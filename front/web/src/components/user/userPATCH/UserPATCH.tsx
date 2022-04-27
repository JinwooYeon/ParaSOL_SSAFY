import { Components } from "components/Components";

export const UserPATCH = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/client",
    method: "PATCH",
    detail: "회원 수정",
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
