import { starSolid, starRegular } from "assets"; 

const formatEvaluate = (number) => {
    const totalReviews = {
        1: 50,
        2: 65,
        3: 90,
        4: 122,
        5: 154
    };
    
    return (
        <span id="evaluate">
            {[...Array(5)].map((_, index) => (
                <span key={index}>
                    <img 
                        src={index < number ? starSolid : starRegular} 
                        alt="icon"
                    />
                </span>
            ))}
            <span id="totalEvaluate"> ({totalReviews[number] || 0} đánh giá)</span>
        </span>
    );
};

export default formatEvaluate;