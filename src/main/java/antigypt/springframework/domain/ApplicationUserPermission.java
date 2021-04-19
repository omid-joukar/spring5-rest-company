package antigypt.springframework.domain;

public enum ApplicationUserPermission {
    PRODUCT_BUY("product:buy"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write");

    private String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
