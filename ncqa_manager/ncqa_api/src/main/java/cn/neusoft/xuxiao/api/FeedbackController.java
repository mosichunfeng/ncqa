package cn.neusoft.xuxiao.api;

import cn.neusoft.xuxiao.entity.FeedBack;
import cn.neusoft.xuxiao.service.IFeedBackService;
import cn.neusoft.xuxiao.util.CommonUtil;
import cn.neusoft.xuxiao.util.StatusConstant;
import com.magicbeans.base.RestBaseController;
import com.magicbeans.base.ajax.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户反馈
 */
@Controller
@ResponseBody
public class FeedbackController extends RestBaseController {
    private static Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private IFeedBackService feedBackService;

    @RequestMapping("/submitFeedback")
    public ResponseData submitFeedback(String user_id,String title,String content,String phone_type){
        if(CommonUtil.isEmpty(user_id,title,content)){
            return buildFailureJson(StatusConstant.BUSINESS_EXCEPTION, "参数填写不完整!");
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setUserId(user_id);
        feedBack.setTitle(title);
        feedBack.setPhoneType(phone_type);
        feedBack.setContent(content);
        feedBackService.save(feedBack);
        return buildSuccessCodeJson(200, "成功!");
    }
}
