package cc.cary.vel.sys.controllers;

import cc.cary.vel.common.annotation.Limit;
import cc.cary.vel.common.authentication.ShiroHelper;
import cc.cary.vel.common.entities.LimitType;
import cc.cary.vel.common.libs.RedisKeys;
import cc.cary.vel.common.libs.ResultData;
import cc.cary.vel.sys.services.ILoginService;
import cc.cary.vel.sys.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * LoginController
 *
 * @author Cary
 * @date 2021/05/22
 */
@RestController
@Validated
@RequestMapping("/site")
public class SiteController {

  @Autowired
  private ILoginService loginService;
  @Autowired
  private ShiroHelper shiroHelper;

  @PostMapping("/login")
  public ResultData login(@Valid LoginVo loginVo) {
    return ResultData.ok().put("token", loginService.adminLogin(loginVo));
  }

  @GetMapping("/current-user-info")
  public ResultData info() {
    return ResultData.ok()
        .put("user", shiroHelper.getCurrentUser())
        .put("permissionInfo", shiroHelper.getCurrentUserAuthorizationInfo());
  }

  @GetMapping("/logout")
  public ResultData logout() {
    shiroHelper.logout();
    return ResultData.ok();
  }

  @GetMapping("/captcha")
  @Limit(name = "获取验证码", key = "captcha", count = 3, period = 5, limitType = LimitType.IP)
  public ResultData captcha() {
    String key = RedisKeys.CAPTCHA_PREFIX + UUID.randomUUID();
    return ResultData.ok()
        .put("key", key)
        .put("captcha", loginService.captcha(key));
  }
}
