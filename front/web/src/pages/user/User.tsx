import UserLoginPOST from "components/user/userLoginPOST";
import OauthDelete from "components/user/oauthDELETE";
import OauthPOST from "components/user/oauthPOST";
import UserDelete from "components/user/userDELETE";
import UserPATCH from "components/user/userPATCH";
import UserRegisterPOST from "components/user/userRegisterPOST";
import UserPasswordPOST from "components/user/userPasswordPOST";
import UserGET from "components/user/userGET";
import { Box, Paper, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";
import UserIdcheckPOST from "components/user/userIdcheckPOST";
import UserTokenPOST from "components/user/userTokenPOST";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  color: theme.palette.text.secondary,
  fontSize: 17,
}));

export const User = () => {
  return (
    <>
      <Box sx={{ width: "100%" }}>
        <Stack spacing={2}>
          {/* 로그인 */}
          <Item>
            <UserLoginPOST />
          </Item>
          {/* 아이디 중복 체크 */}
          <Item>
            <UserIdcheckPOST />
          </Item>
          {/* 회원 등록 */}
          <Item>
            <UserRegisterPOST />
          </Item>
          {/* 비밀번호 수정 */}
          <Item>
            <UserPATCH />
          </Item>
          {/* 회원 탈퇴 */}
          <Item>
            <UserDelete />
          </Item>
          {/* 내 정보 조회 */}
          <Item>
            <UserGET />
          </Item>
          {/* 새로운 인증 토큰 요청 */}
          <Item>
            <UserTokenPOST />
          </Item>
          {/* OAUTH 회원 등록 */}
          <Item>
            <OauthPOST />
          </Item>
          {/* OAUTH 회원 탈퇴 */}
          <Item>
            <OauthDelete />
          </Item>
          {/* 비밀번호 재설정 */}
          <Item>
            <UserPasswordPOST />
          </Item>
        </Stack>
      </Box>
    </>
  );
};
