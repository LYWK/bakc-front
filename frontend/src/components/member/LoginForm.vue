<template>
<div>
  <Nav></Nav>
  <form>
    <div class="form-group">
      <label for="id">ID:</label>
      <input type="text" class="form-control" v-model="customerId"   id="id" placeholder="Enter id">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" v-model="password" placeholder="Enter password">
    </div>
    <div class="checkbox">
      <label><input type="checkbox"> Remember me</label>
    </div>
    <button class="btn btn-default" @click="count">count</button>
    <button  class="btn btn-default" @click="deleteById">deleteById</button>
    <button  class="btn btn-default" @click="existsById">existsById</button>
    <button  class="btn btn-default" @click="findAll">findAll</button>
     <button  class="btn btn-default" @click="save">save</button>
    <button  class="btn btn-default" @click="login">login</button>

  </form>
  <Footer></Footer>
</div>
</template>

<script>
import Nav from '@/components/common/Nav.vue'
import Footer from'@/components/common/Footer.vue'
import axios from 'axios'
import { resolve } from 'url';
export default {
  data(){
    return{
      context: 'http://localhost:9000/customers',
      customerId : 'hong',
      customerName: '홍길동',
      password : '1234',
      ssn : '885929',
      phone : '8454656',
      city : 'seoul',
      address:"YMCA",
      postalcode:"123456",
      photo:"kim.jpg"
    }
  },
  components: {
    Nav,
    Footer
  },
methods:{//내부 제이슨 
 /*      get(){   
         axios.get(`${this.context}/customers/count`)
         .then(d=>{
             alert(`SUCCESS : ${d.data}`)
         })
         .catch(e=>{
             alert('ERROR')
         })
      },     
      post(){
        axios.post('/customers').then(d=>{
          alert(`post연동성공: ${d.data.result}`)
        })
      },
      put(){
        axios.put('/customers/id').then(d=>{
            alert(`put connect success: ${d.data.result}`)
        })
      },
      del(){
        axios.delete('/customers/id').then(d=>{
          alert(`del connect success: ${d.data.result}`)
        })
        
      }  */
      count(){
        axios.get(`${this.context}/count`)
        .then(res=>{
          alert(`SUCCESS : ${res.data}`)
        })
        .catch(e=>{
          alert('ERROR')
          })
      },
      deleteById(){
        axios.delete(`${this.context}/1`)
        .then(res=>{alert(`SUCCESS2 : ${res.data}`)
        }).catch(e=>{
          alert('ERROR')
        })
      },

      existsById(){
        axios.get(`${this.context}/exists/1`)
        .then(res=>{
          alert(`SUCCESS2 : ${res.data}`)
        })
        .catch(e=>{
          alert('ERROR')
        })
      },
      findAll(){
        axios.get(`${this.context}`)
        .then(res=>{
          alert(`findAll(): ${res.data[0].customerName}`)
        })
      },
      findById(){
          
      },
      save(){
        let data = {
          id: 40,
          customerId : this.customerId,
          customerName : this.customerName,
          password : this.password,
          ssn : this.ssn,
          phone : this.phone,
          city : this.city,
          address : this.address,
          postalcode : this.postalcode,
          photo : this.photo
        }
        let headers = {'Content-Type' : 'application/json',
                        'Authorization' : 'JWT fefege..'}

        axios.post(`${this.context}`, JSON.stringify(data),{headers : headers})
        .then(res=>{
          alert(`save() SUCCESS: ${res.data}`)
        })
        .catch(e=>{alert('ERROR')

        })
      },
      login(){
        let data = {
                customerId : this.customerId,
                password : this.password
        }
        let headers = {'Content-Type' : 'application/json',
                        'Authorization' : 'JWT fefege..'}

        axios.post(`${this.context}/login`, JSON.stringify(data),{headers : headers})
        .then(res=>{
          alert(`login() SUCCESS: ${res.data}`)
        })
        .catch(e=>{alert('ERROR')

        })

      }
      // deleteById(){
      //   axios.delete('/${this.context}/id')
      //   .then(d=>{
      //     alert(`deleteById() SUCESS : ${d.data.result}`)
      //   })
      // }
  }
}
</script>

<style scoped>
</style>