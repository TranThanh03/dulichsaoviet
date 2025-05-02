export const setLoading = (isLoading) => {
    const loader = document.querySelector(".global-loader-custom");
    const mainPage = document.querySelector(".page-saoviet");
    
    if (loader) {
        if (isLoading) {
            loader.classList.remove("hidden");
        } else {
            loader.classList.add("hidden");
        }

        if(mainPage) {
            mainPage.style.opacity = isLoading ? "0" : "1";
        }
    }
};