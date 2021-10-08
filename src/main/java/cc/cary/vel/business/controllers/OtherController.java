package cc.cary.vel.business.controllers;

import cc.cary.vel.business.entities.Tinymce;
import cc.cary.vel.business.services.ITinymceService;
import cc.cary.vel.common.libs.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * OtherController
 *
 * @author Cary
 * @date 2021/05/22
 */
@RestController
@RequestMapping("/other")
public class OtherController {
  @Autowired
  private ITinymceService tinymceService;

  @GetMapping("/get-tinymce")
  public ResultData getTinymce() {
    return ResultData.ok().put("content", tinymceService.getById(1L).getContent());
  }

  @PostMapping("/save-tinymce")
  public ResultData saveTinymce(@RequestParam String content) {
    Tinymce tinymce = tinymceService.getById(1L);
    tinymce.setContent(content);
    tinymceService.updateById(tinymce);
    return ResultData.ok();
  }
}
