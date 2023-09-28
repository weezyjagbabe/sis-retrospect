package com.sis.scrum.retrospect.util;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Solomon Bortey on 22/09/2023 <borteys@yahoo.com>
 * @project retrospect
 */

/**
 * The type Link util.
 */
public final class LinkUtil {

    /**
     * The constant REL_COLLECTION.
     */
    public static final String REL_COLLECTION = "collection";
    /**
     * The constant REL_NEXT.
     */
    public static final String REL_NEXT = "next";
    /**
     * The constant REL_PREV.
     */
    public static final String REL_PREV = "prev";
    /**
     * The constant REL_FIRST.
     */
    public static final String REL_FIRST = "first";
    /**
     * The constant REL_LAST.
     */
    public static final String REL_LAST = "last";

    private LinkUtil() {
        throw new AssertionError();
    }

    //

    /**
     * Create link header string.
     *
     * @param uri the uri
     * @param rel the rel
     * @return the string
     */
    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

}