export const setLoading = (isLoading) => {
    const loader = document.querySelector(".global-loader");
    const mainPage = document.querySelector(".page-saoviet");
    
    if (loader) {
        loader.style.opacity = isLoading ? "1" : "0";

        if(mainPage) {
            mainPage.style.opacity = isLoading ? "0" : "1";
        }
    }
};