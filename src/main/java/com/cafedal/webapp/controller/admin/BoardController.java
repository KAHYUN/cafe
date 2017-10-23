package com.cafedal.webapp.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafedal.webapp.dao.NoticeDao;
import com.cafedal.webapp.dao.NoticeFileDao;
import com.cafedal.webapp.entity.Notice;
import com.cafedal.webapp.entity.NoticeFile;
import com.cafedal.webapp.entity.NoticeView;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {
   
   @Autowired
   private NoticeDao noticeDao;
   
   @Autowired
   private NoticeFileDao noticeFileDao;
   
   @RequestMapping("notice")
   public String notice(
         @RequestParam(value="p", defaultValue="1") Integer page,
         @RequestParam(value="f", defaultValue="title") String field,
         @RequestParam(value="q", defaultValue="") String query, Model model) {
      
      List<NoticeView> list = noticeDao.getList(page, field, query);
      
      model.addAttribute("list", list);
      
      return "admin.board.notice.list";
   }
   
   @RequestMapping("notice/{id}")
   public String noticeDetail(@PathVariable("id") String id, Model model) {
      
      
      model.addAttribute("n", noticeDao.get(id));
      model.addAttribute("prev", noticeDao.getPrev(id));
      model.addAttribute("next", noticeDao.getNext(id));

      return "admin.board.notice.detail";
   }
   
   @RequestMapping(value="notice/reg", method=RequestMethod.GET)
   public String noticeReg() {
      
   
      return "admin.board.notice.reg";
   }
   
   @RequestMapping(value="notice/reg", method=RequestMethod.POST)
   /*public String noticeReg(String title, String content) */
   public String noticeReg(Notice notice, String aa, MultipartFile file, HttpServletRequest request) 
         throws IOException {
      
      // 날짜 얻는 방법 1
      Date curDate = new Date();
      //curDate.getYear();
      
      // 날짜 얻는 방법 2
      Calendar cal = Calendar.getInstance();
      Date curDate2 = cal.getTime();
      int year = cal.get(Calendar.YEAR);
      
      // 날짜 얻는 방법 3
      //SimpleDateFormat fmt = new SimpleDateFormat("hh:mm:ss");
      //SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
      //String year = fmt.format(curDate);
      
      String nextId = noticeDao.getNextId();
         
      ServletContext ctx = request.getServletContext();
      String path = ctx.getRealPath(String.format("/resource/customer/notice/%d/%s", year,nextId));
                                    /*"/resource/customer/notice"+ year+"/"+nextId*/
         
      System.out.println(path);
         
      File f = new File(path);
         
      if(!f.exists()){
         if(!f.mkdirs())
            System.out.println("디렉토리를 생성할 수가 없습니다.");
      }
       
      path += File.separator+file.getOriginalFilename();
      File f2 = new File(path);
       
      InputStream fis = file.getInputStream();
      OutputStream fos = new FileOutputStream(f2);
       
      byte[] buf = new byte[1024];
       
      int size = 0;
      while((size = fis.read(buf)) > 0)
    	  fos.write(buf, 0, size);
      
      fos.close();
      fis.close();
       
      
      //file.getInputStream();
      String fileName = file.getOriginalFilename();
      System.out.println(fileName);
      
      
      String writerId = "newlec";
      System.out.println(notice.getTitle());
      //int row = noticeDao.insert(title, content, writerId);
      //noticeDao.insert(new Notice(title, content, writerId));
      
      notice.setWriterId(writerId);
      int row = noticeDao.insert(notice);
      
      noticeFileDao.insert(new NoticeFile(null, fileName, nextId));
      
      /*Connection con = ?;
      con.setAutoCommit(false);
      
      Statement st = con.createStatement();
      st.executeQuery(;)
      st.executeUpdate(sql);
      .getClass().getName()
      
      con.commit();
      con.rollback();*/
      
      return "redirect:../notice";
   }
}           
