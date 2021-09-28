import React from "react";
import styled from "styled-components";


const Square = styled.button`
    height: 50px;
    width: 50px;
    background-color: red;
    color: white;
    padding: 0px 0px;
    border: 0px;
    border-radius: 0px;
    outline: 0;
    box-shadow :0px 0px 0 px white;
`

function clickMe() {
    alert('You Clicked Me!');
}

function Space() {
    return (
        <Square className="Space" onClick={clickMe}/>
    );
}

export default Space;