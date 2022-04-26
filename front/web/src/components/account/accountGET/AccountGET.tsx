import { Components } from "components/Components";

export const AccountGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account",
    method: "GET",
    detail: "계좌 목록 조회",
    completed: false,
  };
  const requestBody = {
    AuthorizedClientInfo: [
      {
        value: "access_token",
        type: "string",
        required: true,
      },
      {
        value: "hashed_resident_number",
        type: "hash_string",
        required: true,
      },
    ],
    token: [
      {
        value: "jwt",
        type: "string",
        required: true,
      },
    ],
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
