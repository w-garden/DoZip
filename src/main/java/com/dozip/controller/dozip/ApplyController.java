package com.dozip.controller.dozip;

import com.dozip.service.dozip.apply.ApplyService;
import com.dozip.vo.EstimateVO;
import com.dozip.vo.PartnersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/dozip/*")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @RequestMapping("/apply") //견적신청 페이지 이동
    public ModelAndView apply(ModelAndView mv, String name){
        int count = this.applyService.counter(); //누적견적신청수
        mv.addObject("name",name);
        mv.addObject("count",count);
        mv.setViewName("/dozip/apply/applicationSheet");
        return mv;
    }

    private String est_addr_change(String est_addr) { //주소 변환 함수
        String str[]=est_addr.split(" ");
        if(est_addr.contains("서울") || est_addr.contains("부산") || est_addr.contains("대구") || est_addr.contains("인천") || est_addr.contains("광주") ||
                est_addr.contains("대전") || est_addr.contains("울산") || est_addr.contains("부산") || est_addr.contains("세종")){
            System.out.println("광역시 테스트");
            est_addr=str[0]+" "+str[1];
        }
        else{
            System.out.println("그외 테스트");
            est_addr=str[1]+" "+str[2];
        }
        return "%" + est_addr + "%";
    }//est_addr_change

    @RequestMapping(value = "/search_part/{est_addr}") //근처에 존재하는 파트너스 목록
    public ResponseEntity<List<PartnersVO>> search_part(@PathVariable("est_addr") String est_addr) {
        String p_address=est_addr_change(est_addr);
        ResponseEntity<List<PartnersVO>> entity = null;
        try {
            entity = new ResponseEntity<>(this.applyService.search_part(p_address), HttpStatus.OK); //근처의 파트너스 불러오기
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }//search_part()

    @ResponseBody //근처에 있는 파트너스 숫자
    @RequestMapping("/count_partners")
    public String count_partners(String est_addr) {
        int count = 0;

        String p_address=est_addr_change(est_addr);

        count = applyService.count_partners(p_address); //근처 파트너스 숫자
        System.out.println("검색어 :" + "%" + est_addr + "%");
        System.out.println("검색된 개수 :" + count);
        System.out.println(count);

        String result=String.valueOf(count);
        return result;
    }//count_partners()

    @RequestMapping("apply_ok")
    public String apply_ok(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        EstimateVO e = new EstimateVO();
        PrintWriter out=response.getWriter();
        e.setMem_id((String) session.getAttribute("id")); //현재 로그인된 세션의 아이디 값

        e.setEst_zoning(request.getParameter("est_zoning"));
        e.setEst_use(request.getParameter("est_use"));
        e.setEst_areaP(Integer.parseInt(request.getParameter("areaP")));
        e.setEst_areaM(Float.parseFloat(request.getParameter("areaM")));
        e.setEst_detail(request.getParameter("d")); //배열
        e.setEst_detail01(request.getParameter("group_wallpaper_item"));
        e.setEst_detail02(request.getParameter("group_floor_item"));
        e.setEst_detail03(request.getParameter("d3")); //배열
        e.setEst_detail04(request.getParameter("d4")); //배열
        e.setEst_detail05(request.getParameter("d5")); //배열
        e.setEst_detail06(request.getParameter("group_window_item"));
        e.setEst_detail07(request.getParameter("group_light_item"));
        e.setEst_detail08(request.getParameter("group_door_item"));

        e.setEst_bud(Integer.parseInt(request.getParameter("est_bud").replace(",","")));
        e.setEst_start(request.getParameter("est_start"));
        e.setEst_end(request.getParameter("est_end"));

        e.setEst_name(request.getParameter("name"));
        e.setEst_phone(request.getParameter("phone"));
        e.setEst_addr(request.getParameter("pf_addr1"));
        e.setEst_desc(request.getParameter("paragraph"));

        String bn = request.getParameter("bNum");
        System.out.println("선택한 회사 : "+bn);

        if(bn != null) {
            String[] array=bn.split("/");
            for (int i = 0; i < array.length; i++) {
                e.setBusinessNum(array[i]);
                this.applyService.applyOk(e);
            }
        }
        else {
            this.applyService.applyOk(e);
        }

//        int count = this.estimateService.counter();
//        e.setStartrow(count);
//        System.out.println(count);

        out.println("<script>");
        out.println("alert('견적 신청이 완료되었습니다.');");
        out.println("location.href = '/dozip/home';");
        out.println("</script>");

        System.out.println(e.toString());

        return null;
    }
}
