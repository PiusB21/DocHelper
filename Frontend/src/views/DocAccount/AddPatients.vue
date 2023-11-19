<script setup>
import Navbar from '../../components/Navbar.vue'
import axios from 'axios'
import {ref} from "vue"
import router from '../../router';
import { defineEmits, defineProps } from 'vue';

const USERS_API_BASE_URL='http://localhost:8080/api/v1/demo'

const {EditedPatient} = defineProps(['patient'])

 const patient={
    name:ref(''),
    gender:ref(''),
    email:ref(''),
    phoneNo:ref('')
}


//to edit a patient's details
const addPatient = async ()=>{
console.log(localStorage.getItem('token'));

   const  requestData = {
        ptName: patient.name.value,
        gender: patient.gender.value,
        ptEmail: patient.email.value,
        ptPhoneNo: patient.phoneNo.value
    }

    axios.post(USERS_API_BASE_URL+'/add',requestData,
        {
            headers: {
            Authorization : 'Bearer '+ localStorage.getItem('token'),
            }
        }

    ).then((response)=>{
        console.log(response)
      
        router.push('/loggedIn')

    }).catch((error)=>{
        console.log('Error occured ; '+ error.message);
    })
    
}

</script>

<template>
    <main>
        <div class="my-5">
            <div class="mx-auto w-25" style="max-width: 100%;">
                <h2 class="text-center mb-3 text-3xl ">Add Patient</h2>
                <form @submit.prevent="addPatient()" autocomplete="off">

                    <!-- name -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="name" class="form">Name</label>
                            <input type="text" name="name" id="name" class="form-control" placeholder="Name" required v-model="patient.name.value" >
                        </div>

                    </div>

                         <!-- gender -->
                         <label for="gender" class="form-label">Gender</label>

                         <div class="form-check">
                            <input type="radio" name="gender" id="male" value="male" required v-model="patient.gender.value" >
                            <label for="male" class="form-check-label">Male</label>
                        </div>

                        <div class="form-check">
                            <input type="radio" name="gender" id="female" value="female" required v-model="patient.gender.value" >
                            <label for="female" class="form-check-label">Female</label>
                        </div>


                    <!-- email -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="email" class="form">Email</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="Email" required v-model="patient.email.value" >
                        </div>

                    </div>

                    <!-- Phone Number -->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="phoneNo" class="form">Phone Number</label>
                            <input type="tel" name="phoneNo" id="phoneNo" class="form-control" placeholder="Phone Number" required v-model="patient.phoneNo.value" >
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input type="submit" value="Submit" class="btn bg-slate-600 hover:bg-slate-500 w-100 text-white">
                        </div>
                    </div>
                
                </form>
            </div>
        </div>
    </main>
</template>

