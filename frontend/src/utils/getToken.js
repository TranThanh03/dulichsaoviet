import Cookies from "js-cookie";

const getToken = (isAdmin) => {
    if(isAdmin) {
        return Cookies.get("tokenAdmin");
    }
    
    return Cookies.get("token");
};

export default getToken;