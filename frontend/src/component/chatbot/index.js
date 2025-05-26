import { memo, useEffect, useState } from 'react';
import './style.scss';
import { FaPaperPlane } from 'react-icons/fa';
import { ChatbotApi } from 'services';

const Chatbot = () => {
    const [messages, setMessages] = useState([
        {
            id: null,
            content: "Xin chào! Tôi là trợ lý ảo Sao Việt, bạn cần hỗ trợ gì?",
            senderType: "chatbot",
            createdTime: null
        }
    ]);
    const [input, setInput] = useState('');
    const [isOpen, setIsOpen] = useState(false);
    const [code, setCode] = useState(null);

    useEffect(() => {
        const fetchGenerateCode = async () => {
            try {
                const codeValue = localStorage.getItem("code");
        
                if (codeValue !== null) {
                    setCode(codeValue);
                    return;
                }
        
                const response = await ChatbotApi.generateCode();
        
                if (response?.code === 2201 && response?.result) {
                    localStorage.setItem("code", response.result);
                    setCode(response.result);
                } else {
                    console.warn("Unexpected response from generate code:", response);
                }
            } catch (error) {
                console.error("Failed to fetch generate code:", error);
            }
        };        

        if (isOpen) {
            fetchGenerateCode();
        }
    }, [isOpen])

    useEffect(() => {
        const fetchMessages = async () => {
            try {
                const response = await ChatbotApi.getMessages(code);

                if (response?.code === 2202) {
                    setMessages(response?.result);
                }
            } catch (error) {
                console.error("Failed to fetch messages: ", error);
            }
        }

        if (isOpen && code) {
            fetchMessages();
        }
    }, [isOpen, code])

    const handleSend = () => {
        // if (!input.trim()) return;

        // setMessages([...messages, { from: 'user', text: input }]);
        // setInput('');

        // setTimeout(() => {
        // setMessages(prev => [
        //     ...prev,
        //     { from: 'bot', text: 'Cảm ơn bạn! Chúng tôi sẽ liên hệ lại sớm.' }
        // ]);
        // }, 1000);
    };

    return (
        <>
            {!isOpen && (
                <div className="chatbot-toggle-btn" onClick={() => setIsOpen(true)}>
                    <i className="fa-solid fa-robot fa-shake"></i>
                </div>
            )}

            {isOpen && (
                <div className="chatbot-container shadow">
                    <div className="chatbot-header bg-primary text-white d-flex justify-content-between align-items-center p-2 fw-bold">
                        <span>💬 Chat với Sao Việt</span>
                        <span className="chatbot-close-btn" onClick={() => setIsOpen(false)}>✖</span>
                    </div>

                    <div className="chatbot-body p-2">
                        {messages.length > 0 && messages.map((msg, index) => (
                            <div key={index} className={`message ${msg.senderType}`}>
                                <div className="message-text">{msg.content}</div>
                            </div>
                        ))}
                    </div>

                    <div className="chatbot-input d-flex border-top p-2">
                        <input type="text" className="form-control me-2" placeholder="Nhập tin nhắn..." value={input} onChange={(e) => setInput(e.target.value)}
                            onKeyDown={(e) => e.key === 'Enter' && handleSend()}
                        />
                            <span className="btn btn-primary d-flex align-items-center" onClick={handleSend}>
                                <FaPaperPlane />
                            </span>
                    </div>
                </div>
            )}
        </>
    );
};

export default memo(Chatbot);