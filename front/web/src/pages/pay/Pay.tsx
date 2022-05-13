import AuthBioDELETE from "components/pay/authBioDELETE";
import AuthBioPOST from "components/pay/authBioPOST";
import { Box, Paper, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";
import BankPOST from "components/bank/bankPOST";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  color: theme.palette.text.secondary,
  fontSize: 17,
}));

export const Pay = () => {
  return (
    <>
      <Box sx={{ width: "100%" }}>
        <Stack spacing={2}>
          {/* 은행 연결 */}
          <Item>
            <BankPOST />
          </Item>
          {/* 생체인증(지문) 등록 및 요청 */}
          <Item>
            <AuthBioPOST />
          </Item>
          {/* 생체인증(지문) 삭제 */}
          <Item>
            <AuthBioDELETE />
          </Item>
        </Stack>
      </Box>
    </>
  );
};
