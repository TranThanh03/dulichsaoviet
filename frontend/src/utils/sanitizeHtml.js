import DOMPurify from "dompurify";

/**
 * @param {string} html
 * @returns {string} 
 */

export const sanitizeHtml = (html) => {
    return DOMPurify.sanitize(html);
};
