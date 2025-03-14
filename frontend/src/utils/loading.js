export const setLoading = (isLoading) => {
    const loader = document.querySelector(".global-loader");
    const mainPage = document.querySelector(".page-saoviet");
    
    if (loader) {
        loader.style.opacity = isLoading ? "1" : "0";
        loader.style.visibility = isLoading ? "visible" : "hidden";

        if(mainPage) {
            mainPage.style.visibility = isLoading ? "hidden" : "visible";
        }
    }
};