import { toast } from 'react-toastify';

export const SuccessToast = (content) => {
    toast.success(content, {
        position: 'top-center',
        autoClose: 3000,
        theme: 'colored',
    });
};

export const ErrorToast = (content) => {
    toast.error(content, {
        position: 'top-center',
        autoClose: 3000,
        theme: 'colored',
    });
};