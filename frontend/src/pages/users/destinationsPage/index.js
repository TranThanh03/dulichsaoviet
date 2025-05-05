import { memo, useState } from 'react';
import { desBanner } from 'assets';
import AnimatedCounter from 'component/counter';
import Banner from 'component/banner';

const DestinationsPage = () => {
    const [activeFilter, setActiveFilter] = useState('all');

    const filters = [
        { label: 'Tất cả', value: 'all' },
        { label: 'Miền Bắc', value: 'b' },
        { label: 'Miền Trung', value: 't' },
        { label: 'Miền Nam', value: 'n' },
    ];

    return (
        <div className='destinations-page'>
            <Banner title={"Điểm đến"} image={desBanner} />

            <section className="popular-destinations-area pt-50 pb-90 rel z-1">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-lg-12">
                            <div className="section-title text-center counter-text-wrap mb-40" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                                <h2>Khám phá các điểm đến phổ biến</h2>

                                <p>Website <AnimatedCounter end={345} duration={2} className="plus" /> trải nghiệm phổ biến nhất mà bạn sẽ nhớ</p>
                                
                                <ul className="destinations-nav mt-30">
                                    {filters.map((filter, index) => (
                                        <li key={index} className={activeFilter === filter.value ? 'active' : ''} onClick={() => setActiveFilter(filter.value)}>
                                            {filter.label}
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div className="container">
                        <div className="row gap-10 destinations-active justify-content-center">
                            {/* @php $count = 0; @endphp
                            @foreach ($tours as $tour)
                                @if ($count % 3 == 2)
                                    <div className="col-md-6 item domain-{{ $tour->domain }}">
                                    @else
                                        < className="col-xl-3 col-md-6 item domain-{{ $tour->domain }}">
                                @endif
                                <div className="destination-item style-two" data-aos-duration="1500" data-aos-offset="50">
                                    <div className="image" style="max-height: 250px">
                                        <a href="#" className="heart"><i className="fas fa-heart"></i></a>
                                        <img src="{{ asset('admin/assets/images/gallery-tours/' . $tour->images[0]) }}"
                                            alt="Destination">
                                    </div>
                                    <div className="content">
                                        <h6 className="tour-title"><a
                                                href="{{ route('tour-detail', ['id' => $tour->tourId]) }}">{{ $tour->title }}</a>
                                        </h6>
                                        <span className="time">{{ $tour->time }}</span>
                                        <a href="{{ route('tour-detail', ['id' => $tour->tourId]) }}" className="more"><i
                                                className="fas fa-chevron-right"></i></a>
                                    </div>
                                </div>
                                */}
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default memo(DestinationsPage);