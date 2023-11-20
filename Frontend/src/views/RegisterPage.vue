<script setup>
import axios from "axios"
import router from "../router/index"
import {ref} from "vue"
import myFooter from "../components/Footer.vue"

const isPasswordValid = ref(true);

const validatePassword=()=> {
      const lowercaseRegex = /(?=.*[a-z])/;
      const uppercaseRegex = /(?=.*[A-Z])/;
      const digitRegex = /(?=.*\d)/;
      const symbolRegex = /(?=.*[@$!%*?&])/;

      isPasswordValid.value = 
        lowercaseRegex.test(user.password.value) &&
        uppercaseRegex.test(user.password.value) &&
        digitRegex.test(user.password.value) &&
        symbolRegex.test(user.password.value);
        return isPasswordValid.value;
    };

    const getValidationMessage = ()=> {
      const messages = [];

      if (!/(?=.*[a-z])/.test(user.password.value)) {
        messages.push('at least one lowercase letter');
      }
      if (!/(?=.*[A-Z])/.test(user.password.value)) {
        messages.push('at least one uppercase letter');
      }
      if (!/(?=.*\d)/.test(user.password.value)) {
        messages.push('at least one digit');
      }
      if (!/(?=.*[@$!%*?&])/.test(user.password.value)) {
        messages.push('at least one symbol');
      }
      return `Password must contain ${messages.join(', ')}.`;
    }

const message= ref('');

const user={
    email:ref(''),
    password:ref(''),
    name:ref(''),
    repeatPassword:ref('')
}


const reset=()=>{
  
     user.email.value =''
     user.password.value=''
     user.name.value=''
     user.repeatPassword.value=''

}


const register=async ()=>{ 

    const registerdata={
        email:user.email.value,
        docName:user.name.value,
        password:user.password.value,
     }
     if(user.password.value != user.repeatPassword.value){
       return;
     }

     await axios.post(import.meta.env.VITE_AUTH_BASE_URL+"/register",registerdata).then((response) => {

        reset()
        
        // Handle the successful response here
           if(response.data.token == null)
             message.value="-USER ALREADY EXISTS-";

            else 
            {
                alert('-SUCCESSFULLY REGISTERED-')
                router.push('/login')
            }

      })
      .catch((error) => {
        // Handle any errors here
        console.error('Error:', error);

      });

}


</script>



<template>

<!-- bg-gradient-to-br from-white via-gray-200 to-[#8B4513] -->

<div class="flex flex-col min-h-screen">
  <div class=" inset-0 flex flex-1 items-center justify-center bg-gradient-to-br from-[white] to-[#71ccd1] bg-opacity-70  ">

<section class="  w-[40%] h-auto bg-white border border-cyan-900 m-auto rounded-lg transition-max-height ease-in-out duration-800">

<div class="container h-full px-6 py-24">
<div 
  class="g-6 flex h-full flex-wrap items-center justify-center lg:justify-between">

  <!-- Left column container with background-->
  <div class="mb-12 md:mb-0 md:w-8/12 lg:w-6/12 ">
    <img
      src="../assets/images/doc3.jpeg"
      class="w-full h-full"
      alt="Phone image" />
      <div class="border-8 border-gradient"></div>
  </div>

  <!-- Right column container with form -->
  <div class="md:w-8/12 lg:ml-6 lg:w-5/12 ">
    <form  @submit.prevent="register" autocomplete="off">
     
      <div class="relative mb-6" >
        <input
        type="text"
        v-model="user.name.value"
        required
        placeholder="Name"
        class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none  transition-max-height duration-300 ease-in-out"
        id="exampleFormControlInput1"
        />
       
      </div>
     

      <div class="relative mb-6" >
        <input
        type="email"
        v-model="user.email.value"
        required
        placeholder="Email Address"
        class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
        id="exampleFormControlInput2"
        />
       
      </div>

      <div class="relative mb-6" >
           
            <input
                type="password"
                v-model="user.password.value"
                required
                placeholder="Password"
                class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-all duration-200"
                id="exampleFormControlInput3"
            />
            <p v-if="!validatePassword() && user.password.value" class="text-green-400 text-sm w-full text-center">{{getValidationMessage()}}</p>

        </div>
        <div class="relative mb-6" >
        
            <input
                type="password"
                v-model="user.repeatPassword.value"
                required
                placeholder="Repeat Password"
                class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-all duration-200"
                id="exampleFormControlInput4"
            />
        
        </div>

        <div v-if="user.repeatPassword.value && user.password.value !== user.repeatPassword.value " class="text-red-400 text-sm w-full text-center">-PASWORDS DO NOT MATCH-</div>
        <div class="text-red-400 text-sm w-full text-center">{{ message }}</div>


      <!-- Submit button -->
      <button
        type="submit"
        class="inline-block w-full rounded bg-slate-600 px-7 pb-2.5 pt-3 text-sm font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
        data-te-ripple-init
        data-te-ripple-color="light">
        Sign up
      </button>

      <!-- Divider -->
      <div class="my-4 flex items-center before:mt-0.5 before:flex-1 before:border-t before:border-neutral-300 after:mt-0.5 after:flex-1 after:border-t after:border-neutral-300">
        <RouterLink
        to="/login"
        class="mx-4 mb-0 text-center font-semibold dark:text-slate-600  hover:text-slate-500">
          SIGN IN
      </RouterLink>
      </div>

    </form>
  </div>
</div>
</div>
</section>

</div>

<myFooter/>
</div>
  
</template>

<style scoped>
/* Define the gradient for the border */
.border-gradient {
    border-image: linear-gradient(to right, transparent, #ffffff) 1;
}
</style>

