package com.bitcamp.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.bitcamp.web.common.lambda.IFunction;
import com.bitcamp.web.common.lambda.ISupplier;
import com.bitcamp.web.common.util.Printer;
import com.bitcamp.web.domain.CustomerDTO;
import com.bitcamp.web.entities.Customer;
import com.bitcamp.web.repository.CustomerRepository;
import com.bitcamp.web.service.CustomerService;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;

/**
 * CustomerController
 */
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired CustomerService customerService;
    @Autowired CustomerDTO customer;
    @Autowired ModelMapper modelMapper;
    @Autowired CustomerRepository repo;
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @GetMapping("/count")
    public long count(){
        System.out.println("====count() 진입");
        return customerService.count();
    }

/*     @DeleteMapping("/{id}")
    public void delete(CustomerDTO dto){
        customerService.delete(entity);
    }

    @DeleteMapping("/{id}")
    public void	deleteAll(){
        customerService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void	deleteAll(Iterable<CustomerDTO> dtos){
        customerService.deleteAll(entities);
    } */

    @DeleteMapping("/{id}")
    public void	deleteById(@PathVariable Long id){
        customerService.deleteById(id);
    }

    @GetMapping("/exists/{id}")
    public boolean	existsById(@PathVariable String id){

        System.out.println("existsById로 넘어온 아이디:" +id);
        Long l = Long.parseLong(id);
               return customerService.existsById(l);
    }

    @GetMapping("")
    public Iterable<CustomerDTO>	findAll(){

        Iterable<Customer> entity = customerService.findAll();

        //List<CustomerDTO> list = new ArrayList<>();


                return null;
    }

 /*    @GetMapping("/{id}")
    public Iterable<CustomerDTO>	findAllById(Iterable<Long> ids){
        Iterable<Customer> entity = customerService.findAllById(ids);

                return null;
    } */

    @GetMapping("/{id}")
    public CustomerDTO	findById(@PathVariable String id){
        System.out.println("findById 안으로 들어온 ID:" +id);
        
       Customer entity = customerService.findById(Long.parseLong(id)).orElseThrow(EntityNotFoundException::new);

       System.out.println(">>>>"+entity.toString());
       CustomerDTO  c = modelMapper.map(entity, CustomerDTO.class);
       System.out.println("조회결과:" + c.toString());

        return c;
    }

    @PostMapping("")
    public HashMap<String,String> save(@RequestBody CustomerDTO dto){
        System.out.println("회원가입" + dto.toString());
        HashMap<String,String> map = new HashMap<>();
  
        Customer entity = new Customer();
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setCustomerId(dto.getCustomerId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.setPhoto(dto.getPhoto());
        entity.setPostalcode(dto.getPostalcode());
        entity.setSsn(dto.getSsn());

  
        System.out.println("엔티니로 바뀐 정보:" + entity.toString());
        customerService.save(entity);
        map.put("result", "SUCCESS");
              return map;
    }
        @PostMapping("/login")     
        public CustomerDTO login(@RequestBody CustomerDTO dto) {
            
                ISupplier fx = (()-> {
                    return repo.findByCustomerIdAndPassword(dto.getCustomerId(),
                            dto.getPassword());
                });
             
                return (CustomerDTO)fx.get();
            }
       
        }
/*     @PostMapping("")
    public Iterable<Customer>	saveAll(Iterable<CustomerDTO> dtos){
          Iterable<Customer> entities = customerService.saveAll(entities);
                return customerService.saveAll(entities);
    } */

    
    
}