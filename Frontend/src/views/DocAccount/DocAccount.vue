<script setup>
    import Navbar from "../../components/Navbar.vue"
  
</script>




<template>

    <main class="h-screen">
    
        <Navbar/>

        <div class="inset-0 z-50 w-fill flex-grow  absolute bg-gradient-to-br from-[white] to-[#71ccd1] bg-opacity-70 overflow-auto  m-0 p-0 mt-5 flex items-center justify-center">
                <div class="bg-white min-h-[50%] h-fill w-fill min-w-[50%] rounded-lg shadow relative">
                    <RouterView  />
                </div>
        </div>

    
    </main>
        
</template>