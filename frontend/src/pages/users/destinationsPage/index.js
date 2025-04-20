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

            <section class="popular-destinations-area pt-50 pb-90 rel z-1">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-12">
                            <div class="section-title text-center counter-text-wrap mb-40" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                                <h2>Khám phá các điểm đến phổ biến</h2>

                                <p>Website <AnimatedCounter end={345} duration={2} className="plus" /> trải nghiệm phổ biến nhất mà bạn sẽ nhớ</p>
                                
                                <ul class="destinations-nav mt-30">
                                    {filters.map((filter, index) => (
                                        <li key={index} className={activeFilter === filter.value ? 'active' : ''} onClick={() => setActiveFilter(filter.value)}>
                                            {filter.label}
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row gap-10 destinations-active justify-content-center">
                            {/* @php $count = 0; @endphp
                            @foreach ($tours as $tour)
                                @if ($count % 3 == 2)
                                    <div class="col-md-6 item domain-{{ $tour->domain }}">
                                    @else
                                        < class="col-xl-3 col-md-6 item domain-{{ $tour->domain }}">
                                @endif
                                <div class="destination-item style-two" data-aos-duration="1500" data-aos-offset="50">
                                    <div class="image" style="max-height: 250px">
                                        <a href="#" class="heart"><i class="fas fa-heart"></i></a>
                                        <img src="{{ asset('admin/assets/images/gallery-tours/' . $tour->images[0]) }}"
                                            alt="Destination">
                                    </div>
                                    <div class="content">
                                        <h6 class="tour-title"><a
                                                href="{{ route('tour-detail', ['id' => $tour->tourId]) }}">{{ $tour->title }}</a>
                                        </h6>
                                        <span class="time">{{ $tour->time }}</span>
                                        <a href="{{ route('tour-detail', ['id' => $tour->tourId]) }}" class="more"><i
                                                class="fas fa-chevron-right"></i></a>
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