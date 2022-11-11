import {searchStore} from "../stores/search"
const store = searchStore();
export function getDocuments(): Promise<Element[]> {
  console.log("OY")


  let mandat:string = store.manda;
  let keyWord:string = store.keyword;
  let start:string = store.start;
  let rows:string = store.rows;

  let fq :String[] = [""];
  
  if(store.affaire != ""){
    fq.push("affaire:" + store.affaire)
  }
  else   if(store.age != ""){
    fq.push(store.age)
  }
  else   if(store.ftype != ""){
    fq.push("ftype:" + store.ftype)
  }
  else   if(store.kh != ""){
    fq.push(store.kh)
  }

    let params = new URLSearchParams({
        q: keyWord,
        core: mandat,
        start: start,
        rows: rows,
        fq:fq.toString()
    }).toString()
    return fetch('http://'+ store.ip +':4567/query/solr/requestadv/string?' + params, {
      method: "POST",

    })
      .then((res) => {
        console.log(res)
        return res.json();
    })
}

export function getFieldValues(field:string): Promise<Element[]> {
  let core :string = store.manda;

  let params = new URLSearchParams({ core:core,field:field}) .toString()

  return fetch('http://'+ store.ip  +':4567/query/solr/fieldnumber/string?' + params, {
    method: "POST",
  })
  .then(res => {
   
    return res.json();
})
}
export function getAllType(){}