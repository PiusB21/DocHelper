<script setup>
import {ref} from 'vue'
import axios from 'axios'
import router from '../../router';
const USERS_API_BASE_URL='http://localhost:8080/api/v1/demo'

const Name = ref('')
const Email = ref('')


let deletedPatient

if(localStorage.getItem('deletedPatient')!=null)
 deletedPatient = JSON.parse(localStorage.getItem('deletedPatient'));

    const pid = deletedPatient.pid; 
    Name.value= deletedPatient.ptName;
    Email.value=deletedPatient.ptEmail;

//to delete a patient's details
const deletePatient = async ()=>{
console.log(localStorage.getItem('token'));


    axios.post(USERS_API_BASE_URL+`/delete/${pid}`,null,
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
    <div class="h-full w-full flex flex-wrap flex-col my-20">
      <p class="text-xl  m-auto" >Are you sure you want to delete <b>{{Name}}</b>?   </p> 

      <button class="btn btn-danger w-1/4 mx-auto mt-6" @click="deletePatient() ">Delete</button>
    </div>
</template>