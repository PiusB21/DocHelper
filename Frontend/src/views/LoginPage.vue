<script setup>
import {ref} from "vue"
import axios from "axios"
const USERS_API_BASE_URL='http://localhost:8080/api/v1/auth'
import router from "../router";
import jwt_decode from "jwt-decode"

const message=ref('')

const user={
     email : ref(""),
     password : ref("")
}

const reset=()=>{
    user.email.value=''
    user.password.value=''
}

const login = ()=>{

const loginData = {
    email : user.email.value,
    password : user.password.value
}

    axios.post(USERS_API_BASE_URL+'/authenticate',loginData).then((response)=>{
        reset()
        console.log(response.data)

        if(response.data.token == null)
            message.value='-INCORRECT USERNAME OR PASSWORD-'
        
        else
        {
            localStorage.setItem('token', response.data.token);
            localStorage.setItem('email', response.data.email);
            router.push('/loggedIn')
        }
   
    })
    .catch((error)=>{
        console.log('Error: '+ error.message)
    })

}

</script>




<template>
  

  <div class="h-screen flex items-center justify-center bg-gradient-to-br from-[white] to-[#71ccd1] bg-opacity-70 ">
    <section class="  w-[40%] min-w-[400px] h-auto bg-white  border border-cyan-900 m-auto rounded-lg transition-max-height ease-in-out duration-800">
  <div class="container h-full px-6 py-24">
    <div
      class="g-6 flex h-full flex-wrap items-center justify-center lg:justify-between">

      <!-- Left column container with background-->
      <div class="mb-12 md:mb-0 md:w-8/12 lg:w-6/12">
        <img
          src="src/assets/images/doc6.jpeg"
          class="w-full transform rotate-0 hover:-rotate-3 transition-transform duration-300 ease-in-out"
          alt="Phone image" />
      </div>

      <!-- Right column container with form -->
      <div class="md:w-8/12 lg:ml-6 lg:w-5/12">
        <form @submit.prevent="login" autocomplete="off">
  
          <div class="relative mb-6">
            <input
            type="text"
            v-model="user.email.value"
            placeholder="Email Address"
            class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
            id="exampleFormControlInput3"
            />
           
          </div>

          <div class="relative mb-6" >
      
            <input
            type="password"
            v-model="user.password.value"
            placeholder="Password"
            class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-all duration-200"
            id="exampleFormControlInput2"
            />
            
            
          </div>

         
          <div class="mb-6 flex items-center justify-between">
         

            <!-- Forgot password link -->
            <a
              href="#!"
              class=" transition duration-150 ease-in-out hover:text-slate-500  text-slate-600 dark:hover:text-primary-500 dark:focus:text-primary-500 dark:active:text-primary-600"
              >Forgot password?</a
            >
          </div>

          <div class="text-red-400 text-sm w-full text-center">{{ message }}</div>


          <!-- Submit button -->
          <button
            type="submit"
            class="inline-block w-full rounded bg-slate-600 hover:bg-slate-500 px-7 pb-2.5 pt-3 text-sm font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
            data-te-ripple-init
            data-te-ripple-color="light">
            Sign in
          </button>

          <!-- Divider -->
          <div
            class="my-4 flex items-center before:mt-0.5 before:flex-1 before:border-t before:border-neutral-300 after:mt-0.5 after:flex-1 after:border-t after:border-neutral-300">

            <RouterLink to="/"
              class="mx-4 mb-0 text-center font-semibold text-slate-600 hover:text-slate-500">
              SIGN UP
            </RouterLink>
          </div>

        </form>
      </div>
    </div>
  </div>
</section>

  </div>
</template>