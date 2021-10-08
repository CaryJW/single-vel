package cc.cary.vel.monitor.controllers;

import cc.cary.vel.common.annotation.Log;
import cc.cary.vel.common.export.IBatchExportService;
import cc.cary.vel.common.libs.QueryRequest;
import cc.cary.vel.common.libs.ResultData;
import cc.cary.vel.monitor.services.ILoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * LoginLogController
 *
 * @author Cary
 * @date 2021/05/22
 */
@RestController
@RequestMapping("/login-log")
public class LoginLogController {
  @Autowired
  private ILoginLogService loginLogService;
  @Autowired
  @Qualifier("loginLogAbstractBatchExportImpl")
  private IBatchExportService batchExportService;

  @GetMapping("/list")
  @RequiresPermissions("login-log:list")
  public ResultData list(QueryRequest request, @RequestParam Map<String, Object> params) {
    return ResultData.ok().put(loginLogService.findForPage(request, params));
  }

  @GetMapping("/delete/{id}")
  @Log("删除登录日志")
  @RequiresPermissions("login-log:delete")
  public ResultData delete(@PathVariable long id) {
    loginLogService.removeById(id);
    return ResultData.ok();
  }

  @GetMapping("/batch-export")
  @Log("批量导出登录日志")
  @RequiresPermissions("login-log:batch-export")
  public ResultData batchExport(@RequestParam Map<String, Object> params) {
    batchExportService.export(params);
    return ResultData.ok();
  }
}
