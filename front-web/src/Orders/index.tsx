import './styles.css'
import StepsHeader from './StepsHeader'
import ProductList from './ProductsList'
import OrderLocation from './OrderLocation'
import { useEffect, useState } from 'react';
import { OrderLocationData, Product } from './types';
import { fetchProducts } from './api';
import React from 'react';

function Orders(){

    const[products, setProducts] = useState<Product[]>([]);
    const[oderLocation, setOrderLocation] = useState<OrderLocationData>();

    console.log(products)

    useEffect( () => {
        fetchProducts()
          .then(response => setProducts(response.data))
          .catch(error => console.log(error))
    }, []);

    return(
        <div className="orders-container">
            <StepsHeader />
            <ProductList products={products}/>
            <OrderLocation onChangeLocation={location => setOrderLocation(location)} />
        </div>
    )
}

export default Orders;