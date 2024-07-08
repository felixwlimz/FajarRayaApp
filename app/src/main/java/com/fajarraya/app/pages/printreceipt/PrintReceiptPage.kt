package com.fajarraya.app.pages.printreceipt

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.utils.Extensions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PrintReceiptPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    printReceiptViewModel: PrintReceiptViewModel = koinViewModel(),
    transactionId: String?
) {

    val view = LocalView.current
    val context = LocalContext.current

    val permission = rememberPermissionState(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val transaction = printReceiptViewModel.transaction.observeAsState()

    val totalitem = printReceiptViewModel.totalitem.observeAsState()
    val totalharga = printReceiptViewModel.totalprice.observeAsState()

    val payment = printReceiptViewModel.payment.observeAsState()


    LaunchedEffect(key1 = Unit) {
        if (transactionId != null) {
            printReceiptViewModel.loadItemData(transactionId)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Preview",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFDBDBDB))
                    .border(1.dp, Color.Black)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "FAJAR RAYA\nMEDAN",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF468BF2),
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Transaction ID : $transactionId",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (transaction.value != null) {
                    for (item in transaction.value!!.items) {
                        Text(
                            text = item.nama,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "${item.quantity} x ${Extensions.toRupiah(item.harga)}")
                            Text(text = Extensions.toRupiah(item.quantity * item.harga))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                HorizontalDivider(color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total Harga")
                    Text(text = totalharga.value?.let { Extensions.toRupiah(it) }
                        ?: "Loading...")
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFADADAD)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Quantity : ${totalitem.value}")
                    Text(text = "Paid with : ${payment.value}")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFADADAD)),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = "Total : ${totalharga.value?.let { Extensions.toRupiah(it) } ?: "Loading..."}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Thank You",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
                HorizontalDivider(color = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            onClick = {
                navHostController.navigate(Screen.History.route) {
                    popUpTo(Screen.Orders.PrintReceiptPage.route + "/$transactionId") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Back to Main Menu")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                  if(permission.status.isGranted){
                      val bitmap = captureComposeScreen(view)
                      val pdf = convertBitmapToPdf(bitmap, context)
                      sharePdfFile(context, pdf)
                  }else{
                      permission.launchPermissionRequest()
                  }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Export to PDF")
        }
    }


}

fun captureComposeScreen(view: View): Bitmap {
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

fun convertBitmapToPdf(bitmap: Bitmap, context: Context) : File {
    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas
    canvas.drawBitmap(bitmap, 0f, 0f, null)
    pdfDocument.finishPage(page)

    val file = File(context.getExternalFilesDir(null), "receipt.pdf")
    pdfDocument.writeTo(FileOutputStream(file))
    pdfDocument.close()

    Toast.makeText(context, "PDF exported successfully", Toast.LENGTH_SHORT).show()

    return file
}

fun sharePdfFile(context: Context, pdfFile: File) {
    val uri = FileProvider.getUriForFile(
        context,
        context.packageName + ".provider",
        pdfFile
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(intent, "Share PDF using:"))
}