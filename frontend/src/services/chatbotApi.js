import axiosInstance from "utils/axiosInstance";

const ChatbotApi = {
    generateCode: () => {
        return axiosInstance.get("/api/v1/chatbot/generate-code");
    },
    getMessages: (code) => {
        return axiosInstance.get(`/api/v1/chatbot/${code}`);
    },
};

export default ChatbotApi;