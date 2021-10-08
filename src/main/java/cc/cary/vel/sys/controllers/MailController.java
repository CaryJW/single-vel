package cc.cary.vel.sys.controllers;

import cc.cary.vel.common.libs.ResultData;
import cc.cary.vel.sys.services.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送邮件
 *
 * @author Cary
 * @date 2021/8/13
 */
@RestController
@RequestMapping("/mail")
public class MailController {
  @Autowired
  private IMailService mailService;

  @GetMapping("/send-captcha")
  public ResultData sendCaptcha(@RequestParam String addressee,
                                @RequestParam String verifyCode
  ) {
    String subject = "vel验证码通知";
    mailService.sendCaptcha(addressee, subject, verifyCode);
    return ResultData.ok();
  }
}
