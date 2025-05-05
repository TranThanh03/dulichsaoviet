export const setLoading = (isLoading) => {
    const loader = document.querySelector(".global-loader-custom");
    const mainPage = document.querySelector(".page-saoviet");
    
    if (loader) {
        if (isLoading) {
            loader.classList.add("show");
        } else {
            loader.classList.remove("show");
        }

        if(mainPage) {
            mainPage.style.opacity = isLoading ? "0" : "1";
        }
    }
};