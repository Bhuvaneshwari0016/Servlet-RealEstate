const product = [
    {
        id: 0,
        image: 'https://teja10.kuikr.com//r1/20210410/ak_717_1431124909-1618058241_700x700.png',
        title:' Cityscape Residences',
        price:3500000,
    },
    {
        id: 1,
        image: 'https://is1-3.housingcdn.com/01c16c28/bdbccdb1d34f2a492f8894e25336b770/v0/medium/4_bhk_apartment-for-sale-eta_ii-Greater+Noida-others.jpg',
        title: 'Prim Rose Hills',
        price:  5000000,
    },
    {
        id: 2,
        image: 'https://is1-3.housingcdn.com/4f2250e8/e79915e5e410205429faa170a00314ce/v0/fs/saral_sky_suites-chandkheda-ahmedabad-saral_infrastructure.jpeg',
        title: 'The Gardenia',
        price: 4000000,
    },
    {
        id: 3,
        image: 'https://greenhomes.ind.in/img/Ongoing-projects/GREEN-CLASSIC-ELEVATION.jpg',
        title: 'Gold Mist Apartment',
        price:  4000000,
    }
   
 ];
 const categories = [...new Set(product.map((item)=>
    {return item}))]
    let i=0;
 document.getElementById('root').innerHTML = categories.map((item)=>
 {
    var {image, title, price} = item;
    return(
        `<div class='box'>
            <div class='img-box'>
                <img class='images' src=${image}></img>
            </div>
        <div class='bottom'>
        <p>${title}</p>
        <h2> ${price}.00</h2>`+
        "<button onclick='addtocart("+(i++)+")'>Add to cart</button>"+
        `</div>
        </div>`
    )
 }).join('')
 
 var cart =[];
 
 function addtocart(a){
    cart.push({...categories[a]});
    displaycart();
 }
 function delElement(a){
    cart.splice(a, 1);
    displaycart();
 }
 
 function displaycart(){
    let j = 0, total=0;
    document.getElementById("count").innerHTML=cart.length;
    if(cart.length==0){
        document.getElementById('cartItem').innerHTML = "Your cart is empty";
        document.getElementById("total").innerHTML = 0+".00";
    }
    else{
        document.getElementById("cartItem").innerHTML = cart.map((items)=>
        {
            var {image, title, price} = items;
            total=total+price;
            document.getElementById("total").innerHTML = +total+".00";
            return(
                `<div class='cart-item'>
                <div class='row-img'>
                    <img class='rowimg' src=${image}>
                </div>
                <p style='font-size:12px;'>${title}</p>
                <h2 style='font-size: 15px;'> ${price}.00</h2>`+
                "<i class='fa-solid fa-trash' onclick='delElement("+ (j++) +")'></i></div>"
            );
        }).join('');
    }
 
    
 }
 