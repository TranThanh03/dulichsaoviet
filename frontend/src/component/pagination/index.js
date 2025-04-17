import { memo } from 'react';

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
    const pagesToShow = 3;
    let startPage = Math.max(1, currentPage - Math.floor(pagesToShow / 2));
    let endPage = Math.min(totalPages, startPage + pagesToShow - 1);

    if (endPage - startPage + 1 < pagesToShow) {
        startPage = Math.max(1, endPage - pagesToShow + 1);
    }

    const displayedPages = [];
    for (let i = startPage; i <= endPage; i++) {
        displayedPages.push(i);
    }

    return (
        <div className="flex justify-center pt-1 pb-4">
            <ul className="flex items-center space-x-2">
                <li>
                    <button
                        disabled={currentPage === 1}
                        onClick={() => onPageChange(currentPage - 1)}
                        className={`w-10 h-10 flex items-center justify-center rounded-full border border-gray-300 ${
                            currentPage === 1 ? 'text-gray-400 cursor-not-allowed' : 'text-gray-700 hover:bg-gray-100'
                        }`}
                    >
                        <i className="fas fa-chevron-left"></i>
                    </button>
                </li>

                {displayedPages.map((page) => (
                    <li key={page}>
                        <button
                            onClick={() => onPageChange(page)}
                            className={`w-10 h-10 flex items-center justify-center rounded-full border border-gray-300 ${
                                page === currentPage
                                    ? 'bg-yellow-500 text-white border-yellow-500'
                                    : 'text-gray-700 hover:bg-gray-100'
                            }`}
                        >
                            {page}
                        </button>
                    </li>
                ))}

                <li>
                    <button
                        disabled={currentPage >= totalPages}
                        onClick={() => onPageChange(currentPage + 1)}
                        className={`w-10 h-10 flex items-center justify-center rounded-full border border-gray-300 ${
                            currentPage >= totalPages ? 'text-gray-400 cursor-not-allowed' : 'text-gray-700 hover:bg-gray-100'
                        }`}
                    >
                        <i className="fas fa-chevron-right"></i>
                    </button>
                </li>
            </ul>
        </div>
    );
};

export default memo(Pagination)