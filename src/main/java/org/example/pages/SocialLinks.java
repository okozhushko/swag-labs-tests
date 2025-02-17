package org.example.pages;

public enum SocialLinks {
    TWITTER(1, "https://twitter.com"),
    FACEBOOK(2, "https://facebook.com"),
    LINKEDIN(3, "https://linkedin.com");

    private final int linkIndex;
    private final String url;

    SocialLinks(int linkIndex, String url) {
        this.linkIndex = linkIndex;
        this.url = url;
    }

    public int getLinkIndex() {
        return linkIndex;
    }

    public String getUrl() {
        return url;
    }

    public static SocialLinks fromString(String socialLink) {
        return switch (socialLink) {
            case "TWITTER" -> TWITTER;
            case "FACEBOOK" -> FACEBOOK;
            case "LINKEDIN" -> LINKEDIN;
            default -> throw new IllegalArgumentException("Unexpected value: " + socialLink);
        };
    }
}