<script setup lang="ts">

const props = defineProps(
{
    ind: Number,   
    emplacement: String,
    thumbnail:String,
    modtime : String,
    _root_ : String,
    link : Array<String>,
    affaire: String,
    ftype : String,
    filename: String,
    content_type:String,
    _version_:Number,
    racifile: String,
    id: String,
    kh: String,
});

</script>
  

<template>
    <div class="item ">
        <dl>
            <div class="element">
                <h1 class="is-size-4" text-element>{{ ind }}. {{ filename }}</h1>
                <div class="el">                
                    <dt class="">Affaire:  </dt>
                    <dd class="text-element">{{ affaire }}</dd>
                </div>
                <div class="el">
                    <dt class="">Emplacement: </dt>
                    <dd class="text-element">{{ emplacement }}</dd>
                </div>

                <div class="el">
                    <dt class="">Date de Modification: </dt>
                    <dd class="text-element">{{ modtime }}</dd>
                </div>
                <div class="el download">
                    <a :href="`${link}`">Télécharger le fichier</a>
                </div>
            </div>

        </dl>

                <img class="item-img" :src="(`src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg`)" >

    </div>
</template>



<style scoped>


.text-element{
    text-overflow: ellipsis;
white-space: nowrap;
overflow: hidden;
}
.element{
    width: 50%;
    float: left;
}

.download{
    padding-left: 170px;
}
/* make image fit in item */
.item-img{
    width: auto;
    height: 100%;
 object-fit: contain;    
 text-align: center;
    display: block;
}
.el{
    padding-top: 30px;
}

dt {
  float: left;
  width: 30%;
  text-align: right;
  padding-right: 15px;
  margin: 0
}

dd {
  float: left;
  width: 70%;
  padding: 0;
  margin: 0
}

.item{
    height: 250px;
    max-height: 300px;
   /* min-width: 1000px;*/
}

</style>
  