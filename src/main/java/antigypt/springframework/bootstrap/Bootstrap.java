package antigypt.springframework.bootstrap;

import antigypt.springframework.domain.*;
import antigypt.springframework.repositories.PostRepository;
import antigypt.springframework.repositories.RecruitmentRepository;
import antigypt.springframework.repositories.SaleRepository;
import antigypt.springframework.repositories.SlideRepository;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final RecruitmentRepository recruitmentRepository;
    private final SlideRepository slideRepository;
    private final SaleRepository saleRepository;
    private final PostRepository postRepository;

    public Bootstrap(RecruitmentRepository recruitmentRepository, SlideRepository slideRepository, SaleRepository saleRepository, PostRepository postRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.slideRepository = slideRepository;
        this.saleRepository = saleRepository;
        this.postRepository = postRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recruitmentRepository.save(getRecruitment());
        System.out.println("Recruitment added");

        slideRepository.saveAll(getListSlides());
        System.out.println("Slides added");
        postRepository.saveAll(getListPosts());
        System.out.println("Posts added");

        saleRepository.saveAll(getListSales());
        System.out.println("Sales added");
    }

    public List<Slide> getListSlides() {
        Slide slide1 = new Slide();
        slide1.setTopic("GROCERIES");
        slide1.setTitle("TOP SCORER OF THE WEEK");
        slide1.setCover("groceries.jpg");
        Slide slide2 = new Slide();
        slide2.setTopic("MARKET");
        slide2.setTitle("TOP SCORER OF THE WEEK");
        slide2.setCover("market.jpg");
        Slide slide3 = new Slide();
        slide3.setTopic("VEGETABLES");
        slide3.setTitle("TOP SCORER OF THE WEEK");
        slide3.setCover("vegtables.jpg");
        List<Slide> slideList = new ArrayList<>();
        slideList.add(slide1);
        slideList.add(slide2);
        slideList.add(slide3);
        return slideList;
    }
    public List<Sale> getListSales() {
        Sale sale1 = new Sale();
        sale1.setTopic("GROCERIES");
        sale1.setCover("grocery-sale.jpg");
        sale1.setPercent(10L);
        sale1.setContent("");

        Sale sale2 = new Sale();
        sale2.setTopic("Wein");
        sale2.setCover("wein-sale.jpg");
        sale2.setPercent(18L);
        sale2.setContent("");

        Sale sale3 = new Sale();
        sale3.setTopic("HAUSHIOLD");
        sale3.setPercent(20L);
        sale3.setCover("haushold-sale.jpg");
        sale3.setContent("");

        Sale sale4 = new Sale();
        sale4.setTopic("MEAT");
        sale4.setPercent(25L);
        sale4.setCover("meat-sale.jpg");
        sale4.setContent("");

        List<Sale> saleList = new ArrayList<>();
        saleList.add(sale1);
        saleList.add(sale2);
        saleList.add(sale3);
        saleList.add(sale4);
        return saleList;
    }

    public List<Post> getListPosts() {
        Post post1 = new Post();
        post1.setTopic("GROCERIES");
        post1.setCover("groceries.jpg");
        post1.setContent("");

        Post post2 = new Post();
        post2.setTopic("GROCERIES");
        post2.setCover("groceries.jpg");
        post2.setContent("");

        Post post3 = new Post();
        post3.setTopic("GROCERIES");
        post3.setCover("groceries.jpg");
        post3.setContent("");

        Post post4 = new Post();
        post4.setTopic("GROCERIES");
        post4.setCover("groceries.jpg");
        post4.setContent("");

        Post post5 = new Post();
        post5.setTopic("GROCERIES");
        post5.setCover("groceries.jpg");
        post5.setContent("");

        Post post6 = new Post();
        post6.setTopic("GROCERIES");
        post6.setCover("groceries.jpg");
        post6.setContent("");

        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);
        postList.add(post3);
        postList.add(post4);
        postList.add(post5);
        postList.add(post6);
        return postList;
    }

    @SneakyThrows
    public ByteArrayOutputStream convertInputStreamToByteArrayOutputStream(InputStream source){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = source.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer;
    }
    public Byte[] convertByteArrayOutputStreamToWrapperByte(ByteArrayOutputStream source){
        int i = 0 ;
        Byte[] getBytes = new Byte[source.size()];
        for (byte b : source.toByteArray()){
            getBytes[i++] = b;
        }
        return getBytes;
    }
    @SneakyThrows
    public Recruitment getRecruitment(){
        File imageFile = ResourceUtils.getFile("classpath:static/images/recruitments/omid.jpg");
        InputStream photoStream  = new FileInputStream(imageFile);
        File cvFile = ResourceUtils.getFile("classpath:static/pdfs/recruitments/omid.pdf");
        InputStream cvStream = new FileInputStream(cvFile);
        Byte[] getBytesPhoto = convertByteArrayOutputStreamToWrapperByte(convertInputStreamToByteArrayOutputStream(photoStream));
        Byte[] getBytesCv =  convertByteArrayOutputStreamToWrapperByte(convertInputStreamToByteArrayOutputStream(cvStream));

        Address address = new Address();
        address.setAddressLine("Ehamagasse 46-29");
        address.setCity("ghaemshahr");
        address.setCountry("Austria");
        address.setRegion("liesing");
        address.setPostalCode("1230");
        Recruitment recruitment = new Recruitment();
        recruitment.setFirstName("Roghayeh");
        recruitment.setLastName("Alinattaj");
        recruitment.setBirthDate(LocalDate.of(1989,9,5));
        recruitment.setApplicationDate(LocalDate.of(2021,1,2));
        recruitment.setAddress(address);
        recruitment.setDesiredSalary(2500.0);
        recruitment.setDetail("This is my first Apply for Job");
        recruitment.setEmail("bitta.52@gmail.com");
        recruitment.setGender(Gender.FEMALE);
        recruitment.setTitle(Title.ING);
        recruitment.setHomePhone("123456");
        recruitment.setMobilePhone("06605443488");
        recruitment.setPhoto(getBytesPhoto);
        recruitment.setCv(getBytesCv);
        return recruitment;
    }
}
