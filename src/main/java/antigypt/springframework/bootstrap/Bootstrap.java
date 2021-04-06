package antigypt.springframework.bootstrap;

import antigypt.springframework.domain.*;
import antigypt.springframework.repositories.RecruitmentRepository;
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
    public Bootstrap(RecruitmentRepository recruitmentRepository, SlideRepository slideRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.slideRepository = slideRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recruitmentRepository.save(getRecruitment());
        System.out.println("Recruitment added");

        slideRepository.saveAll(getListSlides());
        System.out.println("Slides added");
    }

    public List<Slide> getListSlides() {
        Slide slide1 = new Slide();
        slide1.setTopic("De sss ss ad");
        slide1.setTitle("Top Score of The Week");
        slide1.setCover("groceries.jpg");
        Slide slide2 = new Slide();
        slide2.setTopic("De sss ss ad");
        slide2.setTitle("Top Score of The Week");
        slide2.setCover("market.jpg");
        Slide slide3 = new Slide();
        slide3.setTopic("De sss ss ad");
        slide3.setTitle("Top Score of The Week");
        slide3.setCover("vegtables.jpg");
        List<Slide> slideList = new ArrayList<>();
        slideList.add(slide1);
        slideList.add(slide2);
        slideList.add(slide3);
        return slideList;
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
