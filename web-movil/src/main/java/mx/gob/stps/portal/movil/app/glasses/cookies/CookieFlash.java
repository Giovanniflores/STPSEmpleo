package mx.gob.stps.portal.movil.app.glasses.cookies;

public enum CookieFlash {

    INFO("FLASH_INFO"),
    WARNING("FLASH_WARNING"),
    ERROR("FLASH_ERROR"),
    SUCCESS("FLASH_SUCCESS");

    private final String name;

    public static CookieFlash forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for ReportFormat");
        }
        if (name.equals(INFO.toString())) {
            return INFO;
        } else if (name.equals(WARNING.toString())) {
            return WARNING;
        } else if (name.equals(ERROR.toString())) {
            return ERROR;
        } else if (name.equals(SUCCESS.toString())) {
            return  SUCCESS;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any ReportFormat");
    }

    private CookieFlash(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
