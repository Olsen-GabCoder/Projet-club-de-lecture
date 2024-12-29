package tn.esprit.rolleaters.models;

public class MenuItem {
    private String title;
    private int iconResId;
    private Class<?> targetActivity;

    public MenuItem(String title, int iconResId, Class<?> targetActivity) {
        this.title = title;
        this.iconResId = iconResId;
        this.targetActivity = targetActivity;
    }

    public String getTitle() {
        return title;
    }

    public int getIconResId() {
        return iconResId;
    }

    public Class<?> getTargetActivity() {
        return targetActivity;
    }
}
