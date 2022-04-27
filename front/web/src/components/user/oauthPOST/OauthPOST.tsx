import { useState } from "react";
import { Stack, Divider, Box, Button } from "@mui/material";
import { SubmitHandler } from "react-hook-form";
import axios from "axios";
import { Completed } from "components/Completed";
import { RequestBody } from "components/RequestBody";
import styles from "components/styles";
import { ApiMethod } from "components/Components.styled";
import { Components } from "components/Components";

export const OauthPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/oauth",
    method: "POST",
    detail: "OAuth 회원 등록",
    completed: false,
  };
  const requestBody = {};
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
