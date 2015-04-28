package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.framework.exception.ApplicationException;

public class Basx13BActionHelper {
	
    /**
     * ��ʼ��ҳ��������Ϣ
     * @param request HttpServletRequest
     * @throws ApplicationException
     */
    public static void initialPageSelectItem(HttpServletRequest request,List list) throws ApplicationException
    {
        // ��ȡsession
        HttpSession session = request.getSession(false);
        // ��ȡ
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSQYLYDM) == null) {
            String[][] lyzlArray = new String[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
            	DmVo vo=(DmVo)list.get(i);
            	lyzlArray[i][0] = vo.getDm();
            	lyzlArray[i][1] = vo.getMc();
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSQYLYDM, lyzlArray);
        }
     
    }
    
}