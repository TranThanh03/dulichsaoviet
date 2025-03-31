import { useEffect, useState } from "react";
import { sanitizeHtml } from "utils/sanitizeHtml";

const NotifiSuccess = ({ title, message, isActive, onClose }) => {
    const [visible, setVisible] = useState(false);

    useEffect(() => {
        if (isActive) {
            setVisible(true);

            const timer = setTimeout(() => {
                setVisible(false);
                onClose();
            }, 3000);

            return () => clearTimeout(timer);
        }
    }, [isActive, onClose]);

    return (
        <div className={`position-fixed top-0 start-50 translate-middle-x mt-3 ${visible ? "show" : "hide"}`} style={{ zIndex: 1050, transition: "transform 0.5s ease-in-out" }}>
            {visible && (
                <div className="alert alert-success alert-dismissible fade show shadow-lg" role="alert">
                    <strong>{title}</strong>
                    <p dangerouslySetInnerHTML={{ __html: sanitizeHtml(message) }}></p>
                    <button type="button" className="btn-close" onClick={() => setVisible(false)}></button>
                </div>
            )}
        </div>
    );
};

export default NotifiSuccess;