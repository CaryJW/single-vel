package cc.cary.vel.common.properties;

import cc.cary.vel.common.entities.UploadType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SysProperties
 *
 * @author Cary
 * @date 2021/05/22
 */
@Data
@Component
@ConfigurationProperties(prefix = "sys")
public class SysProperties {
  private String cachePrefix;
  private UploadType uploadType;
  private XssConfig xss = new XssConfig();

  @Data
  public static class XssConfig {
    private Boolean isIncludeRichText;

    private String[] excludes;
  }
}
