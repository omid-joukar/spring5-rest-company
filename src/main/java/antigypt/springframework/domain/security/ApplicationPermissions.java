package antigypt.springframework.domain.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Enumerated;


@Getter
@AllArgsConstructor
public enum ApplicationPermissions {
   PRODUCT_BUY("product:buy"),
   PRODUCT_READ("product:read"),
   PRODUCT_WRITE("product:write");
   private String permission;

}
