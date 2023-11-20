<script setup>
import router from "../../router";
import {ref, onMounted} from "vue";
import axios from "axios"

onMounted(() => {
    let data =  getPatients()
    });


const patients = ref([]);
//to get all the patients data
const getPatients=async ()=>{
    axios.get(import.meta.env.VITE_AUTH_BASE_URL+'/fetch_data',{
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('token')
        },
        data: {
                email: localStorage.getItem('email'), // Pass email in the request body
              }
    }).then((response)=>{
  
        if (response.data != null){
           patients.value= response.data
         }
    
        else router.push('/login');
        return null;

    }).catch((error)=>{
         // Handle any errors here
         console.error('Error:', error);

    })
}
  

//to delete a patient
const deletePatient = async (id)=>{
    axios.post(USERS_API_BASE_URL+`/delete/{id}`,{
        headers: {
            Authorization: 'Bearer '+ localStorage.getItem('token')
        },
    }).then((response)=>{
        if(response.data != null){
            getPatients()
        }

        else
        return null;

    }).catch((error)=> {
        console.log('Error : '+ error.message);
    })
}

const goToEditView = (patient) => {
    localStorage.setItem('editedPatient',  JSON.stringify(patient));
 
}

const goToDeleteView = (patient) => {
    localStorage.setItem('deletedPatient',JSON.stringify(patient));
    router.push('/loggedIn/delete')
}


</script>
<template>
    

    <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center  text-3xl">View Patients</h1>

                    <router-link to="/loggedIn/add" class="btn bg-slate-600 hover:bg-slate-500 text-white ">Add Patient</router-link> 

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Gender</th>
                                <th scope="col">Email</th>
                                <th scope="col">Phone Number</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr v-for="patient in patients" :key="patient.pid">
                            
                                <th scope="row">{{ patient.pid }}</th>
                                <td scope="row">{{ patient.ptName }}</td>
                                <td scope="row">{{ patient.gender }}</td>
                                <td scope="row">{{ patient.ptEmail }}</td>
                                <td scope="row">{{ patient.ptPhoneNo }}</td>

                                <td>
                                    <RouterLink class="btn btn-primary" to="/loggedIn/update"  @click="goToEditView(patient)">Edit</RouterLink>
                                    <button class="btn btn-danger mx-2" @click="goToDeleteView(patient) ">Delete</button>
                                </td>

                            </tr>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
</template>