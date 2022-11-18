import {searchStore} from "../stores/search"

export function getDocuments(currentPage:string): Promise<Element[]> {
  console.log("OY")
  const store = searchStore();

  let mandat:string = store.manda;
  let keyWord:string = store.keyword;
  let start:string = currentPage;
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
  const store = searchStore();
  let core :string = store.manda;
  let keyword :string = store.keyword;

  let params = new URLSearchParams({ q:keyword,core:core,field:field}) .toString()

  return fetch('http://'+ store.ip  +':4567/query/solr/fieldnumber/string?' + params, {
    method: "POST",
  })
  .then(res => {
   
    return res.json();
})
}
export function getAllType(){}