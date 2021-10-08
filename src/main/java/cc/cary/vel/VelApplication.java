package cc.cary.vel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动器
 *
 * @author Cary
 * @date 2021/05/22
 */
@SpringBootApplication
@MapperScan("cc.cary.vel.*.mapper")
@EnableAsync
public class VelApplication {

  public static void main(String[] args) {
    SpringApplication.run(VelApplication.class, args);
  }

}
