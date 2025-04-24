import { format } from 'date-fns';
import { vi } from 'date-fns/locale';

const formatDatetime = (dateString) => {
    const date = new Date(dateString);

    if (dateString.includes('T')) {
        return format(date, 'HH:mm:ss dd-MM-yyyy', { locale: vi });
    } else {
        return format(date, 'dd-MM-yyyy', { locale: vi });
    }
};

export default formatDatetime;