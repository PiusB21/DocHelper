<script setup>

const endSession = ()=>{
    localStorage.clear();
}

</script>



<template>
    <main>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Frontend</a>
                <a class="nav-link flex flex-row" @click="endSession" href="/login">
                    <p class="px-1">Logout</p>
                    <span class="material-symbols-outlined">
                        logout
                     </span>
                </a>
               
              
            </div>
        </nav>
    </main>
</template>