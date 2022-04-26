import { Components } from "components/Components";

export const UserPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/client",
    method: "POST",
    detail: "회원가입",
    completed: false,
  };
  const requestBody = {
    ClientInfo: [
      {
        value: "id",
        type: "string",
        required: true,
      },
      {
        value: "password",
        type: "string",
        required: true,
      },
      {
        value: "name",
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
